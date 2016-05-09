Xor's 1.8 Models [![Build Status](http://ci.yawk.at/job/1.8-Models/badge/icon)](http://ci.yawk.at/job/1.8-Models/)
==========
Wonderfully outdated name, innit?

## Downloads

[Download Page](http://models.xor.boole.io)

[Jenkins](http://ci.yawk.at/job/1.8-Models/)

## About

Handcrafted 1.8 block/item models (handcrafted := writting the json by hand). It's not *strictly* written by hand,
since I write tools from time to time to do batch editing. Many of them can be found in the root directory. 
They are unsupported.

This project's goal is to replace as many of the single-cube/builtin-generated models in Minecraft as possible, while
using what I think is a pretty unique style. I like to avoid pixely stuff when I can, so a lot of my models make extensive
use of rotation and other clever tricks to look "smooth".

## Installation

You can clone this repo directly into your resource pack directory, or you can build a distributable zip with `./build`.

Note that `build` has some compilation flags, which can be seen in the output of `./build --help`:

```
Buildscript for Xor's 1.8 Models
Compile settings:
  -b --no-block-items    : removes block item models
  -m --no-minify         : don't minify json
  -o --output            : set the output file
  -w --preserve-work-dir : don't kill the work dir when  we're done
  -u --collect-cruft     : run collectCruft in the workdir
  -h --help              : print this message
```

## Contributing

If you wanna contribute, I think that's cool! Just remember you'll be contributing under the terms of the license. PRs welcome.

If you want a 'nice' development environment, I suggest running `git clone` in your `resourcepacks` directory, or symlinking
the cloned repo to it. Then, run `ln -s src assets` in the repo root to make the directory a valid pack. The `.gitignore` will
make git ignore this symlink. You'll be able to select this as a resourece pack in Minecraft.

```
cd $MINECRAFT/resourcepacks
git clone https://github.com/drXor/1.8-Models.git
cd 1.8-Models
ln -s src assets
```

## Project structure

The root directory has a collection of files used for building and batch editing. Most are single-use and are there for
history documentation purposes. 

`src` contains the fun stuff. `src/minecraft/textures` has textures, and `src/minecraft/models` contains the models. 
There are a few other components that are less important, like `src/minecraft/blockstates`.

`cruft` is a dumping ground for old files that I don't want to delete quite yet. `./collectCruft` will dump leftover unused
files in this folder.

## Notes

"Official" channel: #think @ irc.spi.gt

There is a branch for every major minecraft release which meaningfully changes the model format.
Since it's pretty stable, I expect it will remain at `1.9` for a while.

This pack does not and *will not* support Fast GFX mode. 

## Legal

If you wanna use my pack in a non-personal context (e.g. a creative work such as a video), read [the license](https://github.com/drXor/1.8-Models/blob/1.9/LICENSE.md). 
A line creding me and a link back to this repo/the website is good enough to keep me happy.

I don't like it when people say "you can't use this without my permission!", especially about publicly distributed mods or texture packs.
Hence, I try to give the user a lot of freedom, but keeping just enough control that people won't steal my work. Pretty much the only
thing I don't want is people claiming this is their work, nor do I want it to get distributed without source (if only because I detest
reposting).
