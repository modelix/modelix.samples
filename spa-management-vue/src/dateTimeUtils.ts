import { format, formatISO, parse, parseISO } from 'date-fns';
import { N_Date, N_Time } from 'metamodel-api-ts/build/dist/L_University_Schedule_Time';

// We (miss)use Date for dates and times. 

export function parseModelDateString(dateString: string): Date {
  return parse(dateString, 'dd.MM.yyyy', new Date())
}

export function formatToModelDateString(date: Date): string {
  return format(date, 'dd.MM.yyyy',)
}

export function parseInputDateString(dateString: string): Date {
  return parseISO(dateString)
}

export function formatToInputDateString(date: Date): string {
  return formatISO(date, { representation: 'date' })
}

export function formatModelDateToInputDateString(date: N_Date | undefined): string | undefined {
  if (date === undefined || date.date === undefined) {
    return undefined
  }
  return formatToInputDateString(parseModelDateString(date.date))
}

export function formatInputDateStringToModelDateString(dateString: string): string {
  return formatToModelDateString(parseInputDateString(dateString))
}

export function formatToModelTimeString(time: Date): string {
  return format(time, 'HH:mm')
}

export function parseModelTimeString(timeString: string): Date {
  return parse(timeString, 'HH:mm', new Date())
}

export function formatToInputTimeString(time: Date): string {
  return format(time, 'HH:mm')
}

export function parseModelInputTimeString(timeString: string): Date {
  return parse(timeString, 'HH:mm', new Date())
}

export function formatModelTimeToInputTimeString(time: N_Time | undefined): string | undefined {
  if (time === undefined || time.time === undefined) {
    return undefined
  }
  return formatToInputTimeString(parseModelTimeString(time.time))
}

export function formatInputTimeStringToModelTimeString(timeString: string): string {
  return formatToModelTimeString(parseModelInputTimeString(timeString))
}