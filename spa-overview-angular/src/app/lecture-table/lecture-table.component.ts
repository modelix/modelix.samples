import { Component, Input } from '@angular/core';
import { LectureOccurence } from '../modelUtils';
import { format, formatISO } from 'date-fns';

@Component({
  selector: 'app-lecture-table',
  templateUrl: './lecture-table.component.html',
  styleUrls: ['./lecture-table.component.css']
})
export class LectureTableComponent {
  @Input() lectureOccurences!: LectureOccurence[]

  formatDate(datetime: Date): string {
    return formatISO(datetime,  { representation: 'date' })
  }
  
  formatTime(datetime: Date): string {
    return format(datetime,  'HH:mm')
  }
}
