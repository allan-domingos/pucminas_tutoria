import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { AuthService } from 'src/app/common/auth.service';

@Component({
  selector: 'sgm-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService : AuthService,
    private readonly confirmationService: ConfirmationService
  ) {}

  ngOnInit(): void {
  }

  get name() {
    return this.authService.name;
  }

  get email() {
    return this.authService.email;
  }

  get loggedIn() {
    return this.authService.isLoggedIn;
  }

  public logout(){
    this.confirmationService.confirm({
      message: `Deseja realmente sair?`,
      header: 'Atenção',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.authService.logout();
      }
    });
  }

}
