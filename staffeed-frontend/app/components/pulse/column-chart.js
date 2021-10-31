import Highcharts from 'ember-highcharts/components/high-charts';
import theme from '../../styles/highchartsTheme';

export default class PulseColumnChartComponent extends Highcharts {
  chartOptions = {
    chart: {
      type: 'column',
    },
    title: {
      text: '',
    },
    xAxis: {
      categories: ['1', '2', '3', '4', '5'],
    },
    yAxis: {
      title: {
        text: 'Response (%)',
      },
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.y:.2f}</b>',
    },
  };
  theme = theme;
}
