import { Component } from '@angular/core';
import { ModelService } from './model.service';
import { getRooms } from './modelUtils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private modelService: ModelService) {
  }

  getRooms() {
    const model = this.modelService.getModel()
    if (model === null) {
      return []
    }
    return getRooms(model)
  }
}
