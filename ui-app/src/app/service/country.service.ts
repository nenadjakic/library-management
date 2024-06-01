import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Country } from "../model/country";
import { PageCountry } from "../model/pageCountry";

@Injectable({
    providedIn: 'root',
  })
  
export class CountryService {
    private baseUrl = 'http://localhost:6061/country';
    private defaultHeaders = new HttpHeaders();

    constructor(private http: HttpClient) {}

    getCountries(): Observable<Country[]> {
        return this.http.get<Country[]>(`${this.baseUrl}`);
    }

    findPage = (pageNumber: number, pageSize: number): Observable<PageCountry> => {

        if (pageNumber === null || pageNumber === undefined) {
            throw new Error('Required parameter pageNumber was null or undefined when calling findPage.');
        }

        if (pageSize === null || pageSize === undefined) {
            throw new Error('Required parameter pageSize was null or undefined when calling findPage.');
        }

        let queryParameters = new HttpParams();

        queryParameters = queryParameters.set('pageNumber', <number>pageNumber);
        queryParameters = queryParameters.set('pageSize', <number>pageSize);        

        let headers = this.defaultHeaders;

        headers = headers.set('Accept', [
            'application/json'
        ]);        

        return this.http.get<PageCountry>(`${this.baseUrl}/page`,
            {
                params: queryParameters,
                headers: headers,
            }
        );
    }

    delete = (id: string): Observable<void> => {
        if (id === null || id === undefined) {
            throw new Error('Required parameter id was null or undefined when calling _delete.');
        }

        return this.http.delete<void>(`${this.baseUrl}/${encodeURIComponent(String(id))}`);
    }
}