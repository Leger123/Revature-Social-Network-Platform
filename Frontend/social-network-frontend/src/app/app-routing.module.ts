import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardPageComponent } from './pages/dashboard-page/dashboard-page.component';
import { FeedPageComponent } from './pages/feed-page/feed-page.component';
import { HomepagePageComponent } from './pages/homepage-page/homepage-page.component';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { UpdatePageComponent } from './pages/update-page/update-page.component';

const routes: Routes = [
  {path: "", component: HomepagePageComponent},
  {path: "dashboard", component: DashboardPageComponent},
  {path: "profile/:id", component: ProfilePageComponent},
  {path: "update", component: UpdatePageComponent},
  {path: "search", component: SearchPageComponent},
  {path: "feed/:id", component: FeedPageComponent},
  {path: "**", component: HomepagePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
