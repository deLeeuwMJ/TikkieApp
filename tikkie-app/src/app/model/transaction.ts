export class TransactionModel implements ITransaction {
  transaction_id: number;
  transaction_code: string;
  transaction_description: string;

  constructor(id: number, code: string, descripton: string){
    this.transaction_id = id;
    this.transaction_code = code;
    this.transaction_description = descripton;
  }
}

export interface ITransaction {
  transaction_id: number;
  transaction_code: string;
  transaction_description: string;
}

export enum TransactionType {
  Payment = 0,
  Request = 1
}
