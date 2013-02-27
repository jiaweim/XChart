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
package com.xeiam.xchart.chart;

import java.util.ArrayList;
import java.util.List;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SwingWrapper;

/**
 * @author timmolter
 */
public class NoBug {

  static void plot(Chart chart, int n) {

    chart.setChartTitle("title");
    chart.setXAxisTitle("X");
    chart.setYAxisTitle("Y");
    List<Number> x = new ArrayList<Number>();
    List<Number> y = new ArrayList<Number>();
    String seriesName = addOneSeries(chart, n, x, y);
  }

  private static String addOneSeries(Chart chart, int n, List<Number> x, List<Number> y) {

    for (int i = 0; i <= 10; i++) {
      x.add(i / 10.);
      y.add(i * n / 10.);
    }
    String seriesName = "series " + n;
    Series series = chart.addSeries(seriesName, x, y);
    chart.setxAxisMinMax(0, 1);
    chart.setyAxisMinMax(-5, 5);
    return seriesName;
  }

  public static void main(String[] args) {

    for (int i = 0; i < 4; i++) {
      Chart chart = new Chart(700, 500);
      plot(chart, i);
      new SwingWrapper(chart).displayChart();
    }
  }

}