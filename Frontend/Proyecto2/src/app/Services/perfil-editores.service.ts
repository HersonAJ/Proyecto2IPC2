import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestConstants } from '../rest-constants';

@Injectable({
  providedIn: 'root'
})
export class PerfilesEditoresService {
  
  private apiURL: string;

  constructor(private http: HttpClient, private restConstants: RestConstants) {
    this.apiURL = `${this.restConstants.getApiURL()}perfilesEditores`;
  }

  obtenerEditores(): Observable<any> {
    return this.http.get<any>(this.apiURL);
  }
}

