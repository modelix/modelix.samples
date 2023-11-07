import { ChangeDetectionStrategy, Component } from '@angular/core';
import { ModelService } from '../model.service';
import { LectureOccurence, getLectureOccurences } from '../modelUtils';
import { format, formatISO } from 'date-fns'

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent {

  constructor(private modelService: ModelService) {

  }

  getLectureOccurences(): LectureOccurence[]  {
    const model = this.modelService.getModel();
    if (model === null) {
      return []
    }
    return getLectureOccurences(model, undefined)
  }
}