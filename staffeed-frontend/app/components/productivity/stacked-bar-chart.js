import Highcharts from 'ember-highcharts/components/high-charts';
import theme from '../../styles/highchartsTheme';

export default class ProductivityStackedBarChartComponent extends Highcharts {
  chartOptions = {
    chart: {
      type: 'column',
    },
    title: {
      text: '',
    },
    xAxis: {
      categories: [
        'Team 1',
        'Team 2',
        'Team 3',
        'Team 4',
        'Team 5',
        'Team 6',
        'Team 7',
      ],
    },
    yAxis: {
      title: {
        text: 'Hours/Day',
      },
    },
    tooltip: {
      headerFormat: '<b>{point.x}</b><br/>',
      pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}',
    },
    plotOptions: {
      column: {
        stacking: 'normal',
      },
    },
  };

  data = [
    {
      name: 'Overtime Hours/Day',
      data: [1, 2, 5, 3, 6, 2, 1],
    },
    {
      name: 'Working Hours/Day',
      data: [7, 8, 8, 6, 8, 9, 1],
    },
  ];

  theme = theme;
}
