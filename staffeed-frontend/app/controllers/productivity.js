import Controller from '@ember/controller';

export default class ProductivityController extends Controller {
  get mockPieChartData1() {
    return [
      {
        name: 'Working Hours',
        colorByPoint: true,
        data: [
          {
            name: 'Overtime',
            y: 78,
          },
          {
            name: 'On Time',
            y: 22,
          },
        ],
      },
    ];
  }
  get mockPieChartData2() {
    return [
      {
        name: 'Working Hours',
        colorByPoint: true,
        data: [
          {
            name: 'Overtime',
            y: 40.6,
          },
          {
            name: 'On Time',
            y: 59.4,
          },
        ],
      },
    ];
  }
  get mockPieChartData3() {
    return [
      {
        name: 'Working Hours',
        colorByPoint: true,
        data: [
          {
            name: 'Overtime',
            y: 87.4,
          },
          {
            name: 'On Time',
            y: 12.6,
          },
        ],
      },
    ];
  }
  get mockPieChartData4() {
    return [
      {
        name: 'Working Hours',
        colorByPoint: true,
        data: [
          {
            name: 'Overtime',
            y: 30.7,
          },
          {
            name: 'On Time',
            y: 69.3,
          },
        ],
      },
    ];
  }
}
