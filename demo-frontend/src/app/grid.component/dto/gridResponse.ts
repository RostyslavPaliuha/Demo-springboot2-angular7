import {Employee} from '../employee';

export interface GridResponse {
  totalPages: number;
  employees: Employee[];
}
