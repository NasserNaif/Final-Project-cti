
export interface UserModel{
  firstname:string
  lastName:string
  id:number
  email:string
  roles:string
  token:string
}

export interface CourseModel{
  id:number | null
  name:string
  number:string
  level:number
  addedBy:number | null
  teacher:string

  lectures:LectureModel[] | null
  courseImage:ImageModel | null

}

export interface LectureModel{
  id:number | null
  name:string
  number:number
  course:CourseModel | null
  comments:CommentModel[] | null
}

export interface CommentModel{
  id:number | null
  title:string
  url:string | null
  createdDate:string | null
  lecture:LectureModel | null
  user:UserModel | null
  attachmant:AttachmentModel | null
}

export interface AttachmentModel{
  id:number | null
  fileName:string | null
  base64:string | null
  comment:CommentModel | null
}

export interface ImageModel{
  id:number | null
  fileName:string
  base64:string
}
