/**
 * Copyright (C) 2013 Xeiam LLC http://xeiam.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.xchart.internal.chartpart.axistickcalculator;

import java.math.BigDecimal;

import com.xeiam.xchart.internal.chartpart.Axis.Direction;
import com.xeiam.xchart.internal.chartpart.AxisPair;
import com.xeiam.xchart.style.StyleManager;

/**
 * This class encapsulates the logic to generate the axis tick mark and axis tick label data for rendering the axis ticks for date axes
 * 
 * @author timmolter
 */
public class DateAxisTickCalculator extends AxisTickCalculator {

  DateFormatter dateFormatter;

  /**
   * Constructor
   * 
   * @param axisDirection
   * @param workingSpace
   * @param minValue
   * @param maxValue
   * @param styleManager
   */
  public DateAxisTickCalculator(Direction axisDirection, int workingSpace, BigDecimal minValue, BigDecimal maxValue, StyleManager styleManager) {

    super(axisDirection, workingSpace, minValue, maxValue, styleManager);
    dateFormatter = new DateFormatter(styleManager);
    calculate();
  }

  private void calculate() {

    // tick space - a percentage of the working space available for ticks, i.e. 95%
    int tickSpace = AxisPair.getTickSpace(workingSpace); // in plot space

    // where the tick should begin in the working space in pixels
    int margin = AxisPair.getTickStartOffset(workingSpace, tickSpace); // in plot space BigDecimal gridStep = getGridStepForDecimal(tickSpace);

    // the span of the data
    long span = Math.abs(maxValue.subtract(minValue).longValue()); // in data space

    long gridStepHint = (long) (span / (double) tickSpace * DEFAULT_TICK_MARK_STEP_HINT_X);

    long timeUnit = dateFormatter.getTimeUnit(gridStepHint);
    BigDecimal gridStep = null;
    int[] steps = dateFormatter.getValidTickStepsMap().get(timeUnit);
    for (int i = 0; i < steps.length - 1; i++) {
      if (gridStepHint < (timeUnit * steps[i] + timeUnit * steps[i + 1]) / 2.0) {
        gridStep = new BigDecimal(timeUnit * steps[i]);
        break;
      }
    }

    BigDecimal firstPosition = getFirstPosition(gridStep);

    // generate all tickLabels and tickLocations from the first to last position
    for (BigDecimal tickPosition = firstPosition; tickPosition.compareTo(maxValue) <= 0; tickPosition = tickPosition.add(gridStep)) {

      tickLabels.add(dateFormatter.formatDate(tickPosition, timeUnit));
      // here we convert tickPosition finally to plot space, i.e. pixels
      int tickLabelPosition = (int) (margin + ((tickPosition.subtract(minValue)).doubleValue() / (maxValue.subtract(minValue)).doubleValue() * tickSpace));
      tickLocations.add(tickLabelPosition);
    }
  }

}