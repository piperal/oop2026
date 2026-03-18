import type { Result } from "./result"

export type Athlete = {
    id:number,
    firstName:string,
    lastName:string,
    number:number,
    results?:Result
}