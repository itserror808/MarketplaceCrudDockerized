# Use a specific Node.js version (v18.13 or later)
FROM node:18.13

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json first
COPY package.json package-lock.json ./

# Install production dependencies
RUN npm ci --only=production

# Copy the entire application code
COPY . .

# Install development dependencies and Angular CLI
RUN npm install --only=development
RUN npm install -g @angular/cli

# Run the Angular development server
CMD ["ng", "serve", "--host", "0.0.0.0"]

# Expose the port on which the Angular application will run
EXPOSE 4200