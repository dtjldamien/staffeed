import Highcharts from 'ember-highcharts/components/high-charts';
import theme from '../../styles/highchartsTheme';

export default class PulsePieChartComponent extends Highcharts {
  chartOptions = {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie',
      height: 100,
    },
    title: {
      text: '',
    },
    plotOptions: {
      pie: {
        size: 80,
        dataLabels: {
          enabled: false,
        },
      },
    },
  };

  chartData = [
    {
      name: 'Jane',
      data: [5, 0, 20],
    },
    {
      name: 'John',
      data: [25, 30, 15],
    },
  ];

  theme = theme;
}
