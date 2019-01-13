import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Employee} from 'src/app/grid.component/employee';
import {GridResponse} from './dto/gridResponse';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {element} from 'protractor';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('search') search: ElementRef;
  public rowData: Employee[];
  public page: any;
  public totalPages: number;
  public employee: Employee;
  public criteria: any;

  constructor(private http: HttpClient, private modalService: NgbModal) {
  }

  columnDefs = [
    {headerName: 'ID', field: 'empID', width: 50},
    {headerName: 'Name', field: 'empName'},
    {headerName: 'Active', field: 'empActive'},
    {headerName: 'Department', field: 'department.dpName'}
  ];


  onKey(event: any) {
    this.http.get<Employee[]>('https://localhost:8443/employees/search', {
      params: {
        criteria: event.target.value.toString(),
        page: '0'
      }
    }).subscribe(response => this.rowData = response);
  }

  onCellClicked(event: any, content) {
    console.log(event.data);
    this.employee = event.data;
    this.employee.empDepartment = event.data.department;

    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(() => {
      this.employee = null;
    });
  }

  onUpdate(event: any) {
    this.employee.empName = event.target.getData();
    this.http.put('https://localhost:8443/employees', this.employee);
  }

  onDelete(event: any) {
    const id: bigint = this.employee.empID;
    this.http.delete('https://localhost:8443/employees', {
      params: {
        id: id.toString()
      }
    });
  }

  ngOnInit() {

    this.http.get<GridResponse>('https://localhost:8443/employees', {
      params: {
        page: '0'
      }
    }).subscribe(response => {
      this.rowData = response.employees;
      this.totalPages = response.totalPages;
    });
  }

  onPagination(event) {
    const ar: any = new RegExp('[\\d.]*\\d+').exec(event.innerHTML.toString())[0];
    console.log(ar);
    const e: any = document.getElementById('search-input');
    const text: string = e.value;
    console.log(text);
    this.page = ar - 1;
    if (text.length>0) {
      this.http.get<Employee[]>('https://localhost:8443/employees/search', {
        params: {
          criteria: text,
          page: this.page
        }
      }).subscribe(response => this.rowData = response);
    }
    this.http.get<GridResponse>('https://localhost:8443/employees', {
      params: {
        page: this.page
      }
    }).subscribe(response => {
      this.rowData = response.employees;
      this.totalPages = response.totalPages;
    });
  }

}
