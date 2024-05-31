import { NgFor } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { MenuItem } from "primeng/api";
import { MenuModule } from 'primeng/menu';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html',
    styleUrl: './app.menu.component.css',
    standalone: true,
    imports: [NgFor,MenuModule],
})
export class AppMenuComponent implements OnInit {
    model: MenuItem[] = [];

    ngOnInit(): void {
        this.model = [
            {
                label: 'Administration',
                icon: 'pi pi-fw pi-cog',
                items: [
                    {
                        label: 'Countries',
                        icon: 'pi pi-fw pi-map',
                        routerLink: ['countries']
                    }
                ]
            }
            
        ];
    }
}