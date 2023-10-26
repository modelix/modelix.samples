import { addDays, isAfter, parse } from 'date-fns';

export interface DateData {
  day: number;
  month: number;
  year: number;
}

export interface TimeData {
  hours: number;
  minutes: number;
  year: number;
}

export function interpretDates(
  startDateString: string | undefined,
  endDateString: string | undefined,
  timeString: string | undefined
): Date[] {
  if (
    startDateString === undefined ||
    endDateString === undefined ||
    timeString === undefined
  ) {
    return [];
  }

  const startDate = parseDateTime(startDateString, timeString);
  const endDate = parseDateTime(endDateString, timeString);

  if (startDate === null || endDate === null) {
    return [];
  }

  const result = [];
  let nextDate = startDate;
  while (!isAfter(nextDate, endDate)) {
    result.push(nextDate);
    nextDate = addDays(nextDate, 7);
  }
  return result;
}

export function parseDateTime(
  dateString: string,
  timeString: string
): Date | null {
  let dateWithoutTime;
  try {
    dateWithoutTime = parse(dateString, 'dd.MM.yyyy', new Date());
  } catch (e) {
    console.error('Could not parse date ', dateString);
    return null;
  }
  let dateWithTime;
  try {
    dateWithTime = parse(timeString, 'HH:mm', dateWithoutTime);
  } catch (e) {
    console.error('Could not parse time ', timeString);
    return null;
  }
  return dateWithTime;
}
