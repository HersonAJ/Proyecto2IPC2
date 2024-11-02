import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { HttpClient } from '@angular/common/http';
import { RevistaService } from '../Services/revista.service';
import { RestConstants } from '../rest-constants';

@Component({
  selector: 'app-ver-archivo',
  standalone: true,
  templateUrl: './ver-archivo.component.html',
  styleUrls: ['./ver-archivo.component.css'],
})
export class VerArchivoComponent implements OnInit {
  pdfSrc: SafeResourceUrl = '';
  idRevista: number = 0;
  idEdicion: number = 0;
  revista: any = {};

  constructor(
    private route: ActivatedRoute,
    private sanitizer: DomSanitizer,
    private http: HttpClient,
    private revistaService: RevistaService,
    private restConstants: RestConstants
  ) {}

  ngOnInit(): void {
    this.idRevista = Number(this.route.snapshot.paramMap.get('idRevista'));
    this.idEdicion = Number(this.route.snapshot.paramMap.get('idEdicion'));
  
    const pdfUrl = `${this.restConstants.getApiURL()}ediciones/pdf/${this.idEdicion}`;
  
    this.http.get(pdfUrl, { responseType: 'blob' }).subscribe(blob => {
      const blobUrl = window.URL.createObjectURL(blob);
      this.pdfSrc = this.sanitizer.bypassSecurityTrustResourceUrl(blobUrl) as SafeResourceUrl;
    }, error => {
      console.error('Error fetching PDF:', error.message, 'Status:', error.status, 'URL:', pdfUrl);
    });
  
    this.revistaService.getRevista(this.idRevista).subscribe(data => {
      this.revista = data;
    });
  }
  
}