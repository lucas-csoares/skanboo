import axios from "axios";

export const http = axios.create({
  // baseURL: "http://localhost:8080/",
  baseURL: "http://172.190.123.122:8080/",
});
