import Model from '@ember-data/model';

export default class ResponseModel extends Model {
  @attr('string') response;
  @attr('string') title;
  @attr() choiceNum;
  @attr('date') submittedOn;
  @attr() user;
  @attr() question;
}
