import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SecretService } from '../services/secret.service';

@Component({
  selector: 'app-secret-create',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './secret-create.component.html',
  styleUrls: ['./secret-create.component.css']
})
export class SecretCreateComponent {
  secret: string = '';
  password: string = '';
  secretLink: string | null = null;

  constructor(private secretService: SecretService) {}

  createSecret() {
    if (this.secret) {
      this.secretService.createSecret(this.secret, this.password).subscribe(
        (response: any) => {
          this.secretLink = `${window.location.origin}/view/${response.id}`;
        },
        (error) => {
          console.error('Error creating secret:', error);
          alert('Failed to create secret. Please try again.');
        }
      );
    }
  }

  copyToClipboard() {
    if (this.secretLink) {
      navigator.clipboard.writeText(this.secretLink)
        .then(() => alert('Link copied to clipboard!'))
        .catch(err => console.error('Failed to copy:', err));
    }
  }
}
