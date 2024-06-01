import { Component, OnInit } from '@angular/core';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { Country } from '../model/country';
import { CountryService } from '../service/country.service';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { Observable } from 'rxjs';

const COUNTRIES: Country[] = [];

@Component({
  selector: 'app-countries',
  standalone: true,
  templateUrl: './countries.component.html',
  styleUrl: './countries.component.css',
  imports: [
    TableModule,
    CardModule,
    ToolbarModule,
    ButtonModule,
    ConfirmDialogModule,
    ToastModule
  ],
  providers: [ConfirmationService],
})
export class CountriesComponent implements OnInit {
  countries = COUNTRIES;
  totalRecords: number = 0;
  rows = 25;

  pageNumber = 0;
  pageSize = this.rows;

  constructor(
    private countryService: CountryService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {}

  pageChange = (event: any) => {
    this.pageNumber = event.first / event.rows;
    this.pageSize = event.rows;
    this.load(this.pageNumber, this.pageSize);
  }

  load = (pageNumber: number, pageSize: number) => {
    this.countryService.findPage(pageNumber, pageSize).subscribe({
      next: (page) => {
        this.countries = page.content!!;
        this.totalRecords = page.totalElements!!
      },
      error: (err) => {
        console.error(err);
      },
      complete: () => {},
    });
  }

  delete = (country: Country) => {
    this.confirmationService.confirm({
      message:
        'Are you sure you want to delete the country:  ' + country.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.countries = this.countries.filter((val) => val.id !== country.id);
        this.countryService.delete(country.id).subscribe({
          next: () => {
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Country ' + country.name + ' deleted.',
              life: 3000,
            });
            this.load(this.pageNumber, this.pageSize);
          }, error: (err) => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: 'Error ocurred. Country ' + country.name + ' not deleted.',
              life: 3000,
            });
          }
        });
        
      },
    });
  };

  ngOnInit(): void {
    this.load(0, this.pageSize);
  }
}
