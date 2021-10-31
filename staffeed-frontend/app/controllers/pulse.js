import Controller from '@ember/controller';

export default class PulseController extends Controller {
  get avgResponseRate() {
    console.log(this.model);
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

  get pieChartBreakdown() {
    let categories = this.model.responsesByCategory.filter(
      (cat) => cat.questions.length > 0
    );

    categories = categories.map((cat) => {
      let responseCount = cat.questions.reduce(
        (prevCount, qn) => {
          qn.responses.forEach(
            (res) => (prevCount[res.choiceNum] += res.percentage)
          );
          return prevCount;
        },
        {
          1: 0,
          2: 0,
          3: 0,
          4: 0,
          5: 0,
        }
      );

      let data = [];
      Object.keys(responseCount).forEach((key) => {
        data.push({
          name: key,
          y: (responseCount[key] /= cat.questions.length),
        });
      });
      return {
        ...cat,
        average: cat.average.toFixed(2),
        chartData: [
          {
            name: 'Response',
            colorByPoint: true,
            data: data,
          },
        ],
      };
    });
    console.log(categories);
    return categories;
  }
}
