package University.Schedule.api.gen;

/*Generated by MPS */

import org.modelix.mps.apigen.runtime.MPSLanguage;
import java.util.List;
import org.modelix.mps.apigen.runtime.AbstractConcept;
import University.Schedule.structure.concepts.CoursesConcept;
import University.Schedule.structure.concepts.DateAndTimeConcept;
import University.Schedule.structure.concepts.LectureConcept;
import University.Schedule.structure.concepts.OneOffConcept;
import University.Schedule.structure.concepts.RecurringConcept;
import University.Schedule.structure.concepts.RoomConcept;
import University.Schedule.structure.concepts.RoomsConcept;
import University.Schedule.structure.concepts.ScheduleConcept;
import java.util.function.Consumer;

public class University_Schedule extends MPSLanguage {
  public static final University_Schedule INSTANCE = new University_Schedule();
  private University_Schedule() {
    super("University.Schedule", "University.Schedule");
    List<AbstractConcept<?>> myConcepts = this.getMyConcepts();
    myConcepts.add(CoursesConcept.INSTANCE);
    myConcepts.add(DateAndTimeConcept.INSTANCE);
    myConcepts.add(LectureConcept.INSTANCE);
    myConcepts.add(OneOffConcept.INSTANCE);
    myConcepts.add(RecurringConcept.INSTANCE);
    myConcepts.add(RoomConcept.INSTANCE);
    myConcepts.add(RoomsConcept.INSTANCE);
    myConcepts.add(ScheduleConcept.INSTANCE);
    myConcepts.forEach(new Consumer<AbstractConcept<?>>() {
      public void accept(AbstractConcept<?> concept) {
        concept.init(University_Schedule.this);
      }
    });
  }
}
