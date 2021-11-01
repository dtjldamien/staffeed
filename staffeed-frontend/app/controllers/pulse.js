import Controller from '@ember/controller';
import { tracked } from '@glimmer/tracking';
import { action } from '@ember/object';

export default class PulseController extends Controller {
  @tracked department = 'FINANCE';

  departments = ['FINANCE', 'INVESTMENT', 'TECHNOLOGY'];

  @action
  setDepartment(event) {
    this.department = event.target.value;
  }

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
    return categories;
  }

  get departmentBreakdown() {
    let depts = this.model.responseByDepartment.filter((dept) =>
      this.departments.includes(dept.department)
    );

    depts = depts.map((dept) => {
      let data = {
        STRESS: [0, 0, 0, 0, 0],
        WORKLOAD: [0, 0, 0, 0, 0],
        SATISFACTION: [0, 0, 0, 0, 0],
      };

      dept.questions.forEach((question) => {
        question.responses.forEach((res, index) => {
          data[question.question.category][index] += res.percentage;
        });
      });

      let res = [];
      for (const key in data) {
        res.push({
          name: key,
          data: data[key],
        });
      }

      return {
        ...dept,
        data: res,
      };
    });
    depts = depts.reduce(
      (prev, curr) => ({ ...prev, [curr.department]: curr }),
      {}
    );
    return depts[this.department];
  }

  get responses() {
    return this.model.responses.map((r) => ({
      ...r,
      average: r.average.toFixed(2),
    }));
  }
}
