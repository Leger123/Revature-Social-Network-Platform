import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { YouTubePlayerModule } from '@angular/youtube-player';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardPageComponent } from './pages/dashboard-page/dashboard-page.component';
import { UpdatePageComponent } from './pages/update-page/update-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { LoginComponent } from './components/login/login.component';
import { CreateFeedComponent } from './components/create-feed/create-feed.component';
import { DisplayFeedComponent } from './components/display-feed/display-feed.component';
import { SearchComponent } from './components/search/search.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { ViewProfileComponent } from './components/view-profile/view-profile.component';
import { UploadComponent } from './components/upload/upload.component';
import { MatButtonModule } from '@angular/material/button';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { Globals } from './globals';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { DisplayCommentComponent } from './components/display-comment/display-comment.component';
import { CreateCommentComponent } from './components/create-comment/create-comment.component';
import { NotificationComponent } from './components/notification/notification.component';
import { HomepagePageComponent } from './pages/homepage-page/homepage-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { FeedPageComponent } from './pages/feed-page/feed-page.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardPageComponent,
    UpdatePageComponent,
    ProfilePageComponent,
    NavbarComponent,
    LoginComponent,
    CreateFeedComponent,
    DisplayFeedComponent,
    SearchComponent,
    RegisterUserComponent,
    UpdateUserComponent,
    ViewProfileComponent,
    UploadComponent,
    ResetPasswordComponent,
    DisplayCommentComponent,
    CreateCommentComponent,
    NotificationComponent,
    HomepagePageComponent,
    SearchPageComponent,
    FeedPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatCardModule,
    MatDialogModule,
    MatIconModule,
    YouTubePlayerModule

  ],
  providers: [Globals],
  bootstrap: [AppComponent]
})
export class AppModule { }
