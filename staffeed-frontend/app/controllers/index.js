import Controller from '@ember/controller';

export default class IndexController extends Controller {
  get avgResponseRate() {
    let rate = this.model.responseRate.questions.reduce(
      (prev, qn) => prev + qn.responseRate,
      0
    );
    rate /= this.model.responseRate.questions.length;
    return (rate * 100).toFixed(1);
  }

  get avgRating() {
    let rating = this.model.responses.reduce(
      (prev, res) => prev + res.average,
      0
    );
    rating /= this.model.responses.length;
    return rating.toFixed(2);
  }

  get numSurveys() {
    return this.model.responses.length;
  }

  get numEmployees() {
    return this.model.responseRate.totalEmployees;
  }

  get mockPieChartData() {
    return [
      {
        name: 'Working Hours',
        colorByPoint: true,
        data: [
          {
            name: 'Overtime',
            y: 40.6,
            sliced: true,
            selected: true,
          },
          {
            name: 'On Time',
            y: 59.4,
          },
        ],
      },
    ];
  }
}
