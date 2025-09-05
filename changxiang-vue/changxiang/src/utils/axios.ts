import 'axios'; 
declare module 'axios' {
  interface InternalAxiosRequestConfig {
    skipAuth?: boolean;
  }
}