FROM node:20 AS build

WORKDIR /app

COPY ui-app/package.json .
COPY ui-app/package-lock.json .
COPY ui-app/angular.json .
COPY ui-app/server.ts .
COPY ui-app/tsconfig.app.json .
COPY ui-app/tsconfig.json .
COPY ui-app/tsconfig.spec.json .
COPY ui-app/src/ ./src/
COPY ui-app/public/ ./public/

RUN npm install -g @angular/cli
RUN npm install
RUN npm run build

CMD ["ng", "serve", "--host", "0.0.0.0"]

#FROM nginx:1.27
#
##COPY .deploy/nginx/nginx.conf /etc/nginx/conf.d/default.conf
##RUN rm /etc/nginx/conf.d/default.conf
##COPY .deploy/nginx/nginx.conf /etc/nginx/conf.d/default.conf
#COPY --from=build /app/dist/ui-app/browser /usr/share/nginx/html
#COPY --from=build /app/dist/ui-app/browser/countries /usr/share/nginx/html
EXPOSE 4200
