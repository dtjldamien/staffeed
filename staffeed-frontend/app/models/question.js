import Model, { attr } from '@ember-data/model';

export default class QuestionModel extends Model {
  @attr('string') id;
  @attr('string') title;
  @attr('string') description;
  @attr() options;
  @attr('string') createdBy;
  @attr('date') createdDate;
  @attr('string') startDate;
  @attr() responses;
  @attr('string') category;
}
