import { parseISO } from "date-fns";
import { interpretDates } from "./dateUtils";

describe("The date interpretation", function() {
  it("returns a single date, if start and end date are the same", function() {
    const dates = interpretDates("01.12.1212", "01.12.1212", "08:04")
    expect(dates).toEqual([parseISO('1212-12-01T08:04:00')])
  });

  it("returns a multiple dates, if start and end date are not same", function() {
    const dates = interpretDates("01.12.1212", "12.12.1212", "08:04")
    expect(dates).toEqual([parseISO('1212-12-01T08:04:00'), parseISO('1212-12-08T08:04:00')])
  });


  it("returns no dates, if the start date is undefined", function() {
    const dates = interpretDates(undefined, "12.12.1212", "08:00")
    expect(dates).toEqual([])
  });

  it("returns no dates, if the end date is undefined", function() {
    const dates = interpretDates("12.12.1212", undefined, "08:00")
    expect(dates).toEqual([])
  });

  it("returns no dates, if the time is undefined", function() {
    const dates = interpretDates("12.12.1212", undefined, "08:00")
    expect(dates).toEqual([])
  });
});
