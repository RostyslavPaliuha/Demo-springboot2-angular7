import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Employee} from 'src/app/grid.component/employee';
import {GridResponse} from './dto/gridResponse';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('search') search: ElementRef;
  public rowData: Employee[];
  page = 1;
  public totalPages: number;
  public employee: Employee;
  public criteria: any;
  public collectionSize: any;

  constructor(private http: HttpClient, private modalService: NgbModal) {
  }

  columnDefs = [
    {headerName: 'ID', field: 'empID', width: 60},
    {headerName: 'Name', field: 'empName'},
    {headerName: 'Active', field: 'empActive'},
    {headerName: 'Department', field: 'department.dpName'}
  ];


  onKey(event: any) {
    if (event.target.value.toString() === '') {
      this.ngOnInit();
    } else {
      this.http.get<Employee[]>('https://localhost:8443/employees/search', {
        params: {
          criteria: event.target.value.toString(),
          page: '0'
        }
      }).subscribe(response => {
        this.rowData = response;
        this.totalPages = response.length;
      });
    }
  }

  onCellClicked(event: any, content) {
    console.log(event.data);
    this.employee = event.data;
    this.employee.empDepartment = event.data.department;

    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(() => {

    });
  }

  onUpdate(event: any) {
    const nameField: any = document.getElementById('empName');
    const activeFied: any = document.getElementById('empActive');
    const departmentField: any = document.getElementById('empDepartment');
    this.employee.empName = nameField.value != null && nameField.value !== '' ? nameField.value : this.employee.empName;
    this.employee.empActive = activeFied.value != null && activeFied.value !== '' ? activeFied.value : this.employee.empActive;
    this.employee.empDepartment.dpName = departmentField.value != null && departmentField.value !== '' ? departmentField.value : this.employee.empDepartment.dpName;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.http.put<Employee>('https://localhost:8443/employees', this.employee, httpOptions).subscribe(value => {
      this.employee = null;
      alert('Updated ' + value.empName);
    });
  }

  onDelete(event: any) {
    const id: bigint = this.employee.empID;
    this.http.delete('https://localhost:8443/employees', {
      params: {
        empID: id.toString()
      }
    }).subscribe(value => {
      alert('Deleted ' + this.employee.empName);
      this.employee = null;
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
      this.collectionSize = this.rowData.length;
    });
  }

  onPagination(event) {
    console.log(this.page);
    let pageCount: any = new RegExp('[\\d.]*\\d+').exec(event.innerHTML.toString())[0];
    pageCount = new RegExp('[\\d.]*\\d+').exec(document.getElementsByClassName('page-item active').item(0).getElementsByClassName('page-link').item(0).innerHTML)[0];
    console.log(pageCount);
    const e: any = document.getElementById('search-input');
    const text: string = e.value;
    console.log(text);
    this.page = pageCount - 1;
    if (text.length > 0) {
      this.http.get<Employee[]>('https://localhost:8443/employees/search', {
        params: {
          criteria: text,
          page: this.page.toString()
        }
      }).subscribe(response => this.rowData = response);
    } else {
      this.http.get<GridResponse>('https://localhost:8443/employees', {
        params: {
          page: this.page.toString()
        }
      }).subscribe(response => {
        this.rowData = response.employees;
        this.totalPages = response.totalPages;
      });
    }
  }
}
