export class RequestModel implements IRequest {
  request_id: number;
  transaction_id: number;
  request_sender_name: string;
  request_receiver_name: string;
  request_creation_date: string;
  request_description: string;
  request_amount: number;

  constructor(id: number, transId: number, senderName: string, receiverName: string, creationDate: string, description: string, amount: number){
    this.request_id = id;
    this.transaction_id = transId;
    this.request_sender_name = senderName;
    this.request_receiver_name = receiverName;
    this.request_creation_date = creationDate;
    this.request_description = description;
    this.request_amount = amount;
  }
}

export interface IRequest {
  request_id: number;
  transaction_id: number;
  request_sender_name: string;
  request_receiver_name: string;
  request_creation_date: string;
  request_description: string;
  request_amount: number;
}
