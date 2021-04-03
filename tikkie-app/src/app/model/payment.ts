export class PaymentModel implements IPayment {
  payment_id: number;
  transaction_id: number;
  payment_sender_name: string;
  payment_creation_date: string;
  payment_description: string;
  payment_amount: number;

  constructor(id: number, transId: number, senderName: string, creationDate: string, description:string, amount: number){
    this.payment_id = id;
    this.transaction_id = transId;
    this.payment_sender_name = senderName;
    this.payment_creation_date = creationDate;
    this.payment_description = description;
    this.payment_amount = amount;
  }
}

export interface IPayment {
  payment_id: number;
  transaction_id: number;
  payment_sender_name: string;
  payment_creation_date: string;
  payment_description: string;
  payment_amount: number;
}
