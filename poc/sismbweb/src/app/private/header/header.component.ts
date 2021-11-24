import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/common/auth.service';
import { ConfirmService } from '../confirm/confirm.service';
import { ConfirmEvent } from '../confirm/model/confirm-event';

@Component({
  selector: 'sgm-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService : AuthService,
    private confirmService: ConfirmService
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
    this.confirmService.emit(new ConfirmEvent(
      'Deseja realmente sair?',
      () => {
        this.authService.logout();
      })
    );
  }

}
