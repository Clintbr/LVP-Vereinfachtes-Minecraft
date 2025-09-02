    class Clint {
      constructor(canvas) {
        this.canvas = canvas;
        this.ctx = this.canvas.getContext('2d');
        this.width = this.canvas.width;
        this.height = this.canvas.height * 0.8;
        this.angle = 4;
        // 3D Object Data
        this.cubes = [
            [
                { x: -0.25, y: -0.25, z: -0.25 }, // 0
                { x:  0.25, y: -0.25, z: -0.25 }, // 1
                { x:  0.25, y:  0.25, z: -0.25 }, // 2
                { x: -0.25, y:  0.25, z: -0.25 }, // 3
                { x: -0.25, y: -0.25, z:  0.25 }, // 4
                { x:  0.25, y: -0.25, z:  0.25 }, // 5
                { x:  0.25, y:  0.25, z:  0.25 }, // 6
                { x: -0.25, y:  0.25, z:  0.25 }, // 7
            ],
            [
              { x: -0.075, y: -0.85, z: -0.075 }, // 0
              { x:  0.075, y: -0.85, z: -0.075 }, // 1
              { x:  0.075, y: -0.7, z: -0.075 }, // 2
              { x: -0.075, y: -0.7, z: -0.075 }, // 3
              { x: -0.075, y: -0.85, z:  0.075 }, // 4
              { x:  0.075, y: -0.85, z:  0.075 }, // 5
              { x:  0.075, y: -0.7, z:  0.075 }, // 6
              { x: -0.075, y: -0.7, z:  0.075 }, // 7

              { x: -0.1, y: -0.7, z: -0.1 }, // 0
              { x:  0.1, y: -0.7, z: -0.1 }, // 1
              { x:  0.1, y: -0.5, z: -0.1 }, // 2
              { x: -0.1, y: -0.5, z: -0.1 }, // 3
              { x: -0.1, y: -0.7, z: 0.1 }, // 4
              { x:  0.1, y: -0.7, z: 0.1 }, // 5
              { x:  0.1, y: -0.5, z: 0.1 }, // 6
              { x: -0.1, y: -0.5, z: 0.1 }, // 7

              { x: -0.1, y: -0.5, z: -0.1}, // 0
              { x:  0.1, y: -0.5, z: -0.1}, // 1
              { x:  0.1, y: -0.25, z: -0.1}, // 2
              { x: -0.1, y: -0.25, z: -0.1}, // 3
              { x: -0.1, y: -0.5, z: -0.025}, // 4
              { x:  0.1, y: -0.5, z: -0.025}, // 5
              { x:  0.1, y: -0.25, z: -0.025}, // 6
              { x: -0.1, y: -0.25, z: -0.025}, // 7 

              { x: -0.1, y: -0.5, z: 0.025}, // 0
              { x:  0.1, y: -0.5, z: 0.025}, // 1
              { x:  0.1, y: -0.25, z: 0.025}, // 2
              { x: -0.1, y: -0.25, z: 0.025}, // 3
              { x: -0.1, y: -0.5, z: 0.1}, // 4
              { x:  0.1, y: -0.5, z: 0.1}, // 5
              { x:  0.1, y: -0.25, z: 0.1}, // 6
              { x: -0.1, y: -0.25, z: 0.1}, // 7                
            ]
        ];

        this.faces = [
            [
              [0, 1, 2, 3], // Back face
              [4, 5, 6, 7], // Front face
              [0, 1, 5, 4], // Bottom face
              [2, 3, 7, 6], // Top face
              [0, 3, 7, 4], // Left face
              [1, 2, 6, 5], // Right face
            ],

            [
              [24, 25, 26, 27], // Back face
              [28, 29, 30, 31], // Front face
              [24, 25, 29, 28], // Bottom face
              [26, 27, 31, 30], // Top face
              [24, 27, 31, 28], // Left face
              [25, 26, 30, 29], // Right face
              
              [16, 17, 18, 19], // Back face
              [20, 21, 22, 23], // Front face
              [16, 17, 21, 20], // Bottom face
              [18, 19, 23, 22], // Top face
              [16, 19, 23, 20], // Left face
              [17, 18, 22, 21], // Right face

              [8, 9, 10, 11], // Back face
              [12, 13, 14, 15], // Front face
              [8, 9, 13, 12], // Bottom face
              [10, 11, 15, 14], // Top face
              [8, 11, 15, 12], // Left face
              [9, 10, 14, 13], // Right face

              [0, 1, 2, 3], // Back face
              [4, 5, 6, 7], // Front face
              [0, 1, 5, 4], // Bottom face
              [2, 3, 7, 6], // Top face.................
              [0, 3, 7, 4], // Left face
              [1, 2, 6, 5], // Right face          
            ],

            [
              [2, 3, 7, 6], // Top face
            ]
        ];
      }

      renderScene(distanceLeft, distanceUp, X, Y, color) {
        this.fillStyle
        this.fillrect(distanceLeft, distanceUp, X, Y, color);
      }

      reset() {
        this.ctx.reset();
      }

      colorStroke(color) {
        if(color == false){
          this.ctx.strokeStyle = "white";
        } else{
          this.ctx.strokeStyle = "black";
        }
      }

      color(color) {
        this.ctx.fillStyle = color;
      }

      project(cube, distance) {
        const scale = 1000;
        const z = cube.z + distance;
        return {
          x: (cube.x / z) * scale + this.width / 2,
          y: (cube.y / z) * scale + this.height / 2
        };
      }

      drawFace(cube, rotatedVertices, face, distance) {
        const projectedFace = face.map(index => this.project(rotatedVertices[index], distance));
        this.ctx.beginPath();
        this.ctx.moveTo(projectedFace[0].x, projectedFace[0].y);
        for (let i = 1; i < projectedFace.length; i++) {
          this.ctx.lineTo(projectedFace[i].x, projectedFace[i].y);
        }
        this.ctx.closePath();
        this.ctx.fill();
        this.ctx.stroke();
        
      }
 
      draw3D(cube, angle, distance, X, Y, Z) {
        const newVertices = this.cubes[cube].map(cube => {
          return { x: cube.x - X, y: cube.y - Y, z: cube.z - Z };
        });

        const rotatedVertices = newVertices.map(cube => {
          const cosA = Math.cos(angle);
          const sinA = Math.sin(angle);
          const x = cube.x * cosA - cube.z * sinA;
          const z = cube.x * sinA + cube.z * cosA;
          return { x, y: cube.y, z };
        });

        const sortedFaces = this.faces[cube]
          .map(face => {
            const avgZ = face.map(index => rotatedVertices[index].z)
              .reduce((sum, z) => sum + z, 0) / face.length;
            return { face, avgZ };
          })
          .sort((a, b) => b.avgZ - a.avgZ);

        sortedFaces.forEach(({ face }) => {
          this.drawFace(cube, rotatedVertices, face, distance);
        });
      }
      draw2D(angle, distance, X, Y, Z) {
          const newVertices = this.cubes[0].map(cube => {
            return { x: cube.x - X, y: cube.y - Y, z: cube.z - Z };
          });
  
          const rotatedVertices = newVertices.map(cube => {
            const cosA = Math.cos(angle);
            const sinA = Math.sin(angle);
            const x = cube.x * cosA - cube.z * sinA;
            const z = cube.x * sinA + cube.z * cosA;
            return { x, y: cube.y, z };
          });
  
          const sortedFaces = this.faces[2]
            .map(face => {
              const avgZ = face.map(index => rotatedVertices[index].z)
                .reduce((sum, z) => sum + z, 0) / face.length;
              return { face, avgZ };
            })
            .sort((a, b) => b.avgZ - a.avgZ);
  
          sortedFaces.forEach(({ face }) => {
            this.drawFace(0, rotatedVertices, face, distance);
          });
        }
    }
    //const cubeScene = new Clint('cubeCanvas');
    //cubeScene.renderScene();