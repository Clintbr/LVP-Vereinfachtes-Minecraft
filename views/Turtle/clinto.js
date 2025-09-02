class Clint {
    constructor(canvas) {
        this.canvas = canvas;
        this.ctx = canvas.getContext('2d');
        this.reset(); /*
        this.target = ctx.TEXTURE_3D;
        this.level = 1;
        this.internalformat = ctx.RGBA;
        this.border = 0;
        this.format = RGBA;
        this.type = ctx.UNSIGNED_BYTE;
        this.z = 0;
        const texture = ctx.createTexture();
        this.imageData = new ImageData(100, 100);
        this.ctx.putImageData(imageData, 20, 20);
        this.dataView = new DataView(new ArrayBuffer(2)) */
    }
    
    //const srgbImageData = context.getImageData(0, 0, 1, 1, { colorSpace: "srgb" });
    //texImage3D(target, level, internalformat, width, height, depth, border, format, type, source)
    //texImage3D(target, level, internalformat, width, height, depth, border, format, type, srcData)
    //texImage3D(target, level, internalformat, width, height, depth, border, format, type, srcData, srcOffset)

    /*texImage3D(width, height, depth) {
        this.ctx.texImage3D(
            this.target,
            this.level,
            this.internalformat = ctx.RGBA,
            width,
            height,
            depth,
            this.border,
            this.format,
            this.type,
            new Uint8Array([0xff, 0x00, 0x00, 0x00]),
          );
        this.ctx.texImage3D(
            this.target,
            this.level,
            this.internalformat = ctx.RGBA,
            width,
            height,
            depth,
            this.border,
            this.format,
            this.type,
            this.imageData,
        );
        this.ctx.texImage3D(
            this.target,
            this.level,
            this.internalformat = ctx.RGBA,
            width,
            height,
            depth,
            this.border,
            this.format,
            this.type,
            this.dataView,
        );
        this.ctx.texImage3D(
            this.target,
            this.level,
            this.internalformat = ctx.RGBA,
            width,
            height,
            depth,
            this.border,
            this.format,
            this.type,
            this.dataView,
        );
    }*/

    reset() {
        //this.ctx.texStorage3D(gl.TEXTURE_3D, 1, gl.RGB8, 256, 256, 256);
        this.ctx.reset();
        this.x = this.canvas.width / 2;
        this.y = this.canvas.height / 2;
        //this.z = (this.canvas.width + this.canvas.height) / 4;
        this.angle;
        this.penDown();
        this.color("black");
    }

    penDown() {
        
        this.isPenDown = true;
    }

    penUp() {
        this.isPenDown = false;
    }

    forward(distance) {
        const radians = (this.angle * Math.PI) / 180;
        const newX = this.x + distance * Math.cos(radians);
        const newY = this.y + distance * Math.sin(radians);

        if (this.isPenDown) {
            this.ctx.beginPath();
            this.ctx.moveTo(this.x, this.y);
            this.ctx.lineTo(newX, newY);
            this.ctx.stroke();
        }

        this.x = newX;
        this.y = newY;
    }

    backward(distance) {
        this.forward(-distance);
    }

    right(degrees) {
        this.angle += degrees;
    }

    left(degrees) {
        this.angle -= degrees;
    }

    color(color) {
        this.ctx.strokeStyle = color;
    }

    lineWidth(width) {
        this.ctx.lineWidth = width;
    }

    text(text, font = '10px sans-serif', align = 'center') {
        const radians = (this.angle * Math.PI) / 180 + Math.PI / 2.0;
        this.ctx.save();
        this.ctx.translate(this.x, this.y);
        this.ctx.rotate(radians);
        this.ctx.font = font;
        this.ctx.fillStyle = this.ctx.strokeStyle;
        this.ctx.textAlign = align;
        this.ctx.fillText(text, 0, 0);
        this.ctx.restore();
    }
    moveTo(x, y) {
        this.x = x;
        this.y = y;
    }

    lineTo(x, y) {
        const originalPenState = this.isPenDown;
        this.isPenDown = true;

        this.ctx.beginPath();
        this.ctx.moveTo(this.x, this.y);
        this.ctx.lineTo(x, y);
        this.ctx.stroke();

        this.x = x;
        this.y = y;

        this.isPenDown = originalPenState;
    }
}