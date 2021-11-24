import { Component, OnInit } from '@angular/core';
import { AlertaService } from 'src/app/alerta/alerta.service';
import { AuthService } from 'src/app/common/auth.service';
import { Funcionario } from 'src/app/model/funcionario';
import { ProfileService } from './profile.service';

@Component({
  selector: 'sgm-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [ProfileService]
})
export class ProfileComponent implements OnInit {

  public funcionario : Funcionario;

  constructor(
    private authService : AuthService,
    private profileService : ProfileService
    ) { }

  ngOnInit(): void {
    this.profileService.obterProfile(this.authService.username).subscribe(
      funcionario => this.funcionario = funcionario,
    );
  }

}
