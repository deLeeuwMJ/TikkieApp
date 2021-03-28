export interface ITransaction {
  id: string;
  payments: IPayment[];
  requests: IRequest[];
}

export interface IPayment {
  fullName: string;
  dateAdded: string;
  amount: number;
}

export interface IRequest {
  senderName: string;
  receiverName: string;
  dateAdded: string;
  amount: number;
}
