import { Component } from '@angular/core';
import { LectureOccurence, getLectureOccurences, getRoom } from '../modelUtils';
import { ModelService } from '../model.service';
import { ActivatedRoute, Params } from '@angular/router';
import { N_Room } from 'metamodel-api-ts/build/dist/L_University_Schedule';

@Component({
  selector: 'app-room-overview',
  templateUrl: './room-overview.component.html',
  styleUrls: ['./room-overview.component.css'],
})
export class RoomOverviewComponent {
  public roomReference!: string;

  constructor(
    private route: ActivatedRoute,
    private modelService: ModelService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      const ref = params['ref']
      if (typeof ref !== 'string') {  
        throw Error(`Expected reference ${ref}.`);
      }
      this.roomReference = ref;
    });
  }

  getRoom(): N_Room | null {
    const model = this.modelService.getModel();
    if (model === null) {
      return null;
    }
    return getRoom(model, this.roomReference);
  }

  getLectureOccurences(): LectureOccurence[] {
    const model = this.modelService.getModel();
    if (model === null) {
      return [];
    }
    return getLectureOccurences(model, this.roomReference);
  }
}
