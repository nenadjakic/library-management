import { Component, OnInit } from "@angular/core";
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { Country } from "../model/country";
import { CountryService } from "../service/country.service";
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { ConfirmationService, MessageService } from "primeng/api";
import { ConfirmDialogModule } from 'primeng/confirmdialog';

const COUNTRIES: Country[] = [];

@Component({
  selector: 'app-countries',
  standalone: true,
  templateUrl: './countries.component.html',
  styleUrl: './countries.component.css',
  imports: [ TableModule, CardModule, ToolbarModule,
    ButtonModule,ConfirmDialogModule
   ],
   providers: [MessageService, ConfirmationService]
})
export class CountriesComponent implements OnInit {
  countries = COUNTRIES;

  constructor(private countryService: CountryService,
    private confirmationService: ConfirmationService
  ) {}

  delete = (country: Country) => {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the country:  ' + country.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
          
      }
  });
  }

  ngOnInit(): void {
    this.countryService.getCountries().subscribe({
      next: (countries) => {
        this.countries = countries
      },
      error: (err) => {
        
        console.error(err);
      },
      complete: () => {  },
    })
  }
}
