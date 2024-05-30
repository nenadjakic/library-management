import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Country } from "../model/country";

@Injectable({
    providedIn: 'root',
  })
  
export class CountryService {
    private baseUrl = 'http://localhost:6061/country';

    constructor(private http: HttpClient) {}

    getCountries(): Observable<Country[]> {
        return this.http.get<Country[]>(`${this.baseUrl}`);
    }
}