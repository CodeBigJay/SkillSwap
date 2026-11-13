export type Skill={id:number;name:string;level:string|null;type:'OFFERED'|'WANTED'};
export type Match={userId:number;username:string;score:number;theyOfferYouWant:string[];theyWantYouOffer:string[]};
export type User={id:number;username:string;email:string};