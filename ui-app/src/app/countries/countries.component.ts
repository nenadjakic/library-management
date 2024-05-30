import { Component, OnInit } from "@angular/core";

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Country } from "../model/country";
import { CountryService } from "../service/country.service";

const COUNTRIES: Country[] = [];

@Component({
  selector: 'app-countries',
  standalone: true,
  templateUrl: './countries.component.html',
  imports: [ MatSlideToggleModule, MatButtonModule, MatTableModule, MatSnackBarModule]
})
export class CountriesComponent implements OnInit {
  columns = [
    {
      columnDef: 'id',
      header: 'Id',
      cell: (element: Country) => `${element.id}`
    },
    {
      columnDef: 'code',
      header: 'Code',
      cell: (element: Country) => `${element.alpha2Code}`
    },
    {
      columnDef: 'name',
      header: 'Name',
      cell: (element: Country) => `${element.name}`
    },
  ]
  title = 'Countries';
  countries = COUNTRIES;
  displayedColumns: string[] = ['id', 'code', 'name'];

  constructor(private countryService: CountryService, private snackBar: MatSnackBar) {}
  ngOnInit(): void {
    this.countryService.getCountries().subscribe({
      next: (countries) => {
        this.countries = countries
        this.snackBar.open("Countries loaded.", undefined, { duration: 3000 });
      },
      error: (err) => {
        this.snackBar.open("Problem with loading data.", undefined, { duration: 3000 });
        console.error(err);
      },
      complete: () => {  },
    })
  }
}
