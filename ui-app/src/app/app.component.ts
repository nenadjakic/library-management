import { Component, OnInit, inject } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CountriesComponent } from "./countries/countries.component";
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable, map, shareReplay } from 'rxjs';
import { AsyncPipe } from '@angular/common';
import { ToolbarModule } from 'primeng/toolbar';
import { SplitButtonModule } from 'primeng/splitbutton';

import { ButtonModule } from 'primeng/button';
import { MenuItem, PrimeIcons } from 'primeng/api';

import { SidebarModule } from 'primeng/sidebar';
import { PanelModule } from 'primeng/panel';
import { AppMenuComponent } from "./layout/app.menu.component";
@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
        RouterOutlet,
        CountriesComponent,
        ButtonModule,
        AsyncPipe,
        RouterLink,
        RouterLinkActive,
        ToolbarModule,
        SplitButtonModule,
        SidebarModule,
        AppMenuComponent,PanelModule
    ]
})
export class AppComponent implements OnInit {
  private breakpointObserver = inject(BreakpointObserver);
  items: MenuItem[] | undefined;
  sidebarVisible: boolean = false;

  isLarge$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Large)
  .pipe(
    map(result => result.matches),
    shareReplay()
  );

  ngOnInit() {
    this.items = [
        {
            label: 'Update',
            icon: 'pi pi-refresh'
        },
        {
            label: 'Delete',
            icon: 'pi pi-times'
        }
    ];
}



}
