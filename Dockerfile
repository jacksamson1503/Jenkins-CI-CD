```dockerfile
FROM nginx:alpine

# Update Alpine packages to the latest security-fixed versions
RUN apk update && \
    apk upgrade --no-cache

# Copy application files
COPY . /usr/share/nginx/html/

# Expose nginx port
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
```
