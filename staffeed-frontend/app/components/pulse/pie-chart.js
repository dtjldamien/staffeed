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
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>',
    },
    plotOptions: {
      pie: {
        size: 80,
        dataLabels: {
          enabled: false,
        },
      },
    },
    accessibility: {
      point: {
        valueSuffix: '%',
      },
    },
  };

  chartData = [
    {
      name: 'Brands',
      colorByPoint: true,
      data: [
        {
          name: 'Chrome',
          y: 61.41,
        },
        {
          name: 'Internet Explorer',
          y: 11.84,
        },
        {
          name: 'Firefox',
          y: 10.85,
        },
        {
          name: 'Edge',
          y: 4.67,
        },
        {
          name: 'Safari',
          y: 4.18,
        },
        {
          name: 'Sogou Explorer',
          y: 1.64,
        },
        {
          name: 'Opera',
          y: 1.6,
        },
        {
          name: 'QQ',
          y: 1.2,
        },
        {
          name: 'Other',
          y: 2.61,
        },
      ],
    },
  ];

  theme = theme;
}
