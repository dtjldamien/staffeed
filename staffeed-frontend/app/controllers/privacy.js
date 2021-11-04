import Controller from '@ember/controller';
import { tracked } from '@glimmer/tracking';
import { action } from '@ember/object';

export default class PrivacyController extends Controller {
  @tracked toggle1 = false;
  @tracked toggle2 = true;
  @tracked toggle3 = true;
  @tracked toggle4 = false;
  @tracked toggle5 = false;

  @action
  setToggle1() {
    this.toggle1 = !this.toggle1;
  }
  @action
  setToggle2() {
    this.toggle2 = !this.toggle2;
  }
  @action
  setToggle3() {
    this.toggle3 = !this.toggle3;
  }
  @action
  setToggle4() {
    this.toggle4 = !this.toggle4;
  }
  @action
  setToggle5() {
    this.toggle5 = !this.toggle5;
  }
}
