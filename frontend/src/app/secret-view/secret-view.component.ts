import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { SecretService } from '../services/secret.service';

@Component({
  selector: 'app-secret-view',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './secret-view.component.html',
  styleUrls: ['./secret-view.component.css']
})
export class SecretViewComponent implements OnInit {
  secretId: string = '';
  password: string = '';
  secretContent: string = '';
  error: string = '';
  requiresPassword: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private secretService: SecretService
  ) { }

  ngOnInit(): void {
    this.secretId = this.route.snapshot.params['id'];
    if (!this.secretId) {
      this.error = 'No secret ID provided';
      return;
    }
    this.checkSecret();
  }

  checkSecret(): void {
    this.error = '';
    this.secretService.checkSecret(this.secretId).subscribe(
      (response) => {
        this.requiresPassword = response.requiresPassword;
        if (!this.requiresPassword) {
          this.viewSecret();
        }
      },
      (error) => {
        if (error.status === 404) {
          this.error = 'This secret does not exist or has already been viewed';
        } else {
          this.error = 'An error occurred while checking the secret. Please try again.';
        }
      }
    );
  }

  viewSecret(): void {
    if (this.requiresPassword && !this.password) {
      this.error = 'Please enter the password to view this secret';
      return;
    }

    this.error = '';
    this.secretService.viewSecret(this.secretId, this.password).subscribe(
      (response) => {
        this.secretContent = response.content;
      },
      (error) => {
        if (error.status === 401) {
          this.error = 'Invalid password';
        } else if (error.status === 404) {
          this.error = 'This secret does not exist or has already been viewed';
        } else {
          this.error = 'An error occurred while retrieving the secret. Please try again.';
        }
      }
    );
  }
}
