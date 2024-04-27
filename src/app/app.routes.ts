import { Routes } from '@angular/router';
import { LoginComponent } from './Pages/login/login.component';
import { RegistrationComponent } from './Pages/registration/registration.component';
import { HeaderComponent } from './Pages/header/header.component';
import { FooterComponent } from './Pages/footer/footer.component';
import { HomeComponent } from './Pages/home/home.component';

export const routes: Routes = [
    {path: '', redirectTo: '/home', pathMatch:'full'}, // هنا اخبرته اذا كان العنوان غير معروف اجعله يذهب الى الصفحه 
    {path: 'home', component: HomeComponent, title: 'Home'},
    {path:'header', component: HeaderComponent, title: 'header'},
    {path: 'login', component: LoginComponent, title: 'Login'},

    {path:'registration', component: RegistrationComponent, title: 'Registration'},
    {path:'Footer', component: FooterComponent, title: 'Footer'},


    //{path: '', redirectTo: '/login', pathMatch:'full'}, // هنا اخبرته ا��ا كان العنوان غير معروف اجعله يذهب الى الصفحه

    //{path: '**', component: NotFoundComponent, title: 'Not Found'}
];
