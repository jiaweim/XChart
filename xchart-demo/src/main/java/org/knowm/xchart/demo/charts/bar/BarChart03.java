/**
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.knowm.xchart.demo.charts.bar;

import java.util.Arrays;

import org.knowm.xchart.Chart;
import org.knowm.xchart.ChartBuilder;
import org.knowm.xchart.StyleManager.ChartType;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;

/**
 * Positive and Negative
 * <p>
 * Demonstrates the following:
 * <ul>
 * <li>Number categories
 * <li>Positive and negative values
 * <li>Single series
 */
public class BarChart03 implements ExampleChart {

  public static void main(String[] args) {

    ExampleChart exampleChart = new BarChart03();
    Chart chart = exampleChart.getChart();
    new SwingWrapper(chart).displayChart();
  }

  @Override
  public Chart getChart() {

    // Create Chart
    Chart chart = new ChartBuilder().chartType(ChartType.Bar).width(800).height(600).title("Score vs. Age").xAxisTitle("Age").yAxisTitle("Score").build();
    chart.addCategorySeries("males", Arrays.asList(new Integer[] { 10, 20, 30, 40 }), Arrays.asList(new Integer[] { 40, -30, -20, -60 }));

    return chart;
  }
}
